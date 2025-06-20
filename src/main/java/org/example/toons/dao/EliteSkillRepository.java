package org.example.toons.dao;

import org.example.toons.model.EliteSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EliteSkillRepository extends JpaRepository<EliteSkill, UUID> {
}
