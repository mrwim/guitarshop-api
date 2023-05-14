package nl.inholland.guitarshopapi.repository;

import nl.inholland.guitarshopapi.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findMemberByUsername(String name);
}
