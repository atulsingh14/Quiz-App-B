package com.zodiac.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zodiac.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
