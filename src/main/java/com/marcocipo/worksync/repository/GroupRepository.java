package com.marcocipo.worksync.repository;

import com.marcocipo.worksync.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//TODO das genau anschauen
public interface GroupRepository extends JpaRepository<Group, Long> {

    boolean existsByName(String name);

    @Query("""
        select (count(g) > 0)
        from Group g
        join g.members m
        where g.id = :groupId and m.id = :userId
    """)
    boolean isMember(@Param("groupId") Long groupId, @Param("userId") Long userId);
}
