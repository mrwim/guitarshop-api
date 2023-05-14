package nl.inholland.guitarshopapi.controller;

import nl.inholland.guitarshopapi.model.dto.LoginDTO;
import nl.inholland.guitarshopapi.model.dto.TokenDTO;
import nl.inholland.guitarshopapi.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Object login(@RequestBody LoginDTO dto) throws Exception {
        return new TokenDTO(
                memberService.login(dto.username(), dto.password())
        );
    }
}
