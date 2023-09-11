package com.huifeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huifeng.entity.BoardEntity;
import com.huifeng.mapper.BoardMapper;
import com.huifeng.service.BoardService;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl extends ServiceImpl<BoardMapper, BoardEntity> implements BoardService{
    @Override
    public Boolean disable(Integer boardId) {
        BoardEntity boardEntity = this.getById(boardId);
        if(boardEntity == null) return false;
        if(boardEntity.getSystem() == true){
            boardEntity.setDisable(true);
            return this.updateById(boardEntity);
        }

        return this.removeById(boardId);
    }
}