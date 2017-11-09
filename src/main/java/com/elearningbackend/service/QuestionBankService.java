/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningbackend.service;

import com.elearningbackend.dto.Pager;
import com.elearningbackend.dto.QuestionBankDto;
import com.elearningbackend.entity.QuestionBank;
import com.elearningbackend.repository.IQuestionBankRepository;
import com.elearningbackend.utility.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author c1508l3694
 */
@Service
@Transactional
public class QuestionBankService implements IQuestionBankService {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private IQuestionBankRepository questionBankRepository;

    @Override
    public Pager<QuestionBankDto> loadAllQuestions(int currentPage, int noOfRowInPage) {
        Page<QuestionBank> pager = questionBankRepository.findAll(new PageRequest(currentPage, noOfRowInPage));
        return paginate(currentPage, pager, noOfRowInPage);
    }

    @Override
    public QuestionBankDto getQuestionByCode(String questionCode) {
        QuestionBank question = questionBankRepository.findOne(questionCode);
        return question == null ? null : mapper.map(question, QuestionBankDto.class);
    }

    @Override
    public Pager<QuestionBankDto> getQuestionByCreator(String creatorUsername, int currentPage, int noOfRowInPage) {
        Page<QuestionBank> pager = questionBankRepository.fetchQuestionByCreator(creatorUsername);
        return paginate(currentPage, pager, noOfRowInPage);
    }

    @Override
    public QuestionBankDto addNewQuestion(QuestionBankDto question) throws Exception {
        try {
            saveQuestion(question);
            return question;
        } catch (Exception e){
            throw new Exception("Cannot add new question", e);
        }
    }

    @Override
    public QuestionBankDto editQuestion(QuestionBankDto question) throws Exception {
        QuestionBankDto questionByCode = getQuestionByCode(question.getQuestionCode());
        if (questionByCode != null) {
            saveQuestion(question);
            return question;
        }
        // sang controller try catch -> throw http 200 + message
        throw new Exception("Cannot edit question");
    }

    @Override
    public QuestionBankDto deleteQuestion(String questionCode) throws Exception {
        QuestionBankDto questionByCode = getQuestionByCode(questionCode);
        if (questionByCode != null) {
            questionBankRepository.delete(mapper.map(questionByCode, QuestionBank.class));
            return questionByCode;
        }
        // sang controller try catch -> throw http 200 + message
        throw new Exception("Cannot delete question");
    }

    Pager<QuestionBankDto> paginate(int currentPage, Page<QuestionBank> pager, int noOfRowInPage) {
        noOfRowInPage = validateNoOfRowInPage(noOfRowInPage);
        currentPage = validateCurrentPage(currentPage);
        Pager<QuestionBankDto> result = new Pager<>();
        result.setCurrentPage(currentPage);
        result.setTotalRow(pager.getTotalElements());
        result.setNoOfRowInPage(noOfRowInPage);
        result.setTotalPage(pager.getTotalElements() / noOfRowInPage);
        List<QuestionBankDto> resultList = pager.getContent().stream().filter(Objects::nonNull).map(
            a -> mapper.map(a, QuestionBankDto.class)).collect(Collectors.toList()
        );
        result.setResults(resultList);
        return result;
    }

    private int validateCurrentPage(int currentPage) {
        return currentPage <= Constants.ZERO ? currentPage = Constants.CURRENT_PAGE_DEFAULT_VALUE
                : currentPage;
    }

    private int validateNoOfRowInPage(int noOfRowInPage) {
        return noOfRowInPage <= Constants.ZERO ? noOfRowInPage = Constants.NO_OF_ROWS_DEFAULT_VALUE
                : noOfRowInPage;
    }

    private void saveQuestion(QuestionBankDto question) {
        questionBankRepository.save(mapper.map(question, QuestionBank.class));
    }
}