package com.dabai.serviceImpl.Comment;

import api.Comment.ReplyService;
import com.dabai.dao.Comment.ReplyMapper;
import com.dabai.dto.Comment.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * @author dabai:
 * <p>
 * 类说明  ReplyService实现类
 */
@DubboService
@Component
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public boolean addReply(Reply reply) {
        return replyMapper.addReply(reply) == 1;
    }

}
