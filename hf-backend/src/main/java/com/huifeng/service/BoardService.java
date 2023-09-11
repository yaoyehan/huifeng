package com.huifeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huifeng.entity.BoardEntity;

public interface BoardService extends IService<BoardEntity>{

    /**
     * 删除看板
     * @param boardId
     * @return
     */
    Boolean disable(Integer boardId);
}
