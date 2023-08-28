package com.zodiac.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import com.zodiac.quizapp.dao.QuestionDao;
import com.zodiac.quizapp.model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  @Autowired
  QuestionDao questionDao;

  public ResponseEntity<List<Question>> getAllQuestions() {
    try {
      return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
    try {
      return new ResponseEntity<List<Question>>(questionDao.findByCategory(category), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

  }

  public ResponseEntity<String> addQuestion(Question question) {
    try {
      questionDao.save(question);
      return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<String>("Question Not Created", HttpStatus.CONFLICT);
  }

  public String deleteQuestion(Question question) {
    questionDao.delete(question);
    return "Deleted";
  }

}
