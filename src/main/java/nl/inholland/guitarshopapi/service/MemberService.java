package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.model.Member;
import nl.inholland.guitarshopapi.model.Role;
import nl.inholland.guitarshopapi.repository.MemberRepository;
import nl.inholland.guitarshopapi.util.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
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

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, new ArrayList<Role>());
            return token;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Credentials");
        }
    }
}
