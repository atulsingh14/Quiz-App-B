package com.zodiac.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zodiac.quizapp.dao.QuestionDao;
import com.zodiac.quizapp.dao.QuizDao;
import com.zodiac.quizapp.model.Question;
import com.zodiac.quizapp.model.QuestionWrapper;
import com.zodiac.quizapp.model.Quiz;

@Service
public class QuizService {

  @Autowired
  QuizDao quizDao;
  @Autowired
  QuestionDao questionDao;

  public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
    List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

    Quiz quiz = new Quiz();
    quiz.setTitle(title);
    quiz.setQuestions(questions);
    quizDao.save(quiz);
    return new ResponseEntity<>("Success", HttpStatus.CREATED);
  }

  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
    Optional<Quiz> quiz = quizDao.findById(id);
    List<Question> questionFromDB = quiz.get().getQuestions();
    List<QuestionWrapper> questionForUser = new ArrayList<>();

    for (Question q : questionFromDB) {
      QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(),
          q.getOption3(), q.getOption4());
      questionForUser.add(qw);
    }

    return new ResponseEntity<>(questionForUser, HttpStatus.OK);
  }

}
