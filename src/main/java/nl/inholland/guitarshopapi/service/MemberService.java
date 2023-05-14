package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.model.Member;
import nl.inholland.guitarshopapi.repository.MemberRepository;
import nl.inholland.guitarshopapi.util.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Member add(Member member) {
        if (memberRepository.findMemberByUsername(member.getUsername()).isEmpty()) {
            member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
            return memberRepository.save(member);
        }
        throw new IllegalArgumentException("Username is already taken");
    }

    public List<Member> getAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }


    public String login(String username, String password) throws Exception {
        // See if a user with the provided username exists
        Member member = this.memberRepository.findMemberByUsername(username).orElse(null);

        // If they don't, throw an exception (handled in our global exception handler)
        if (member == null) {
            throw new AuthenticationException("User not found");
        }

        // If they do, check if the password hash matches
        if (bCryptPasswordEncoder.matches(password, member.getPassword())) {
            // Return a JWT to the client
            return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        } else {
            throw new AuthenticationException("Invalid username/password");
        }
    }
}