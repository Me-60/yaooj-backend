package com.me.yaooj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.yaooj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.me.yaooj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.me.yaooj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.me.yaooj.model.entity.User;
import com.me.yaooj.model.vo.QuestionSubmitVO;


/**
 * @author 25876
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-05-17 18:00:31
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
