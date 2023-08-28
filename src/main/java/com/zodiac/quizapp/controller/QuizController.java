package com.zodiac.quizapp.controller;

import com.zodiac.quizapp.model.QuestionWrapper;
import com.zodiac.quizapp.service.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz")
public class QuizController {

  @Autowired
  QuizService quizService;

  @PostMapping("create")
  public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ,
      @RequestParam String title) {

    return quizService.createQuiz(category, numQ, title);

  }

  @GetMapping("get/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
    return quizService.getQuizQuestions(id);
  }
}
