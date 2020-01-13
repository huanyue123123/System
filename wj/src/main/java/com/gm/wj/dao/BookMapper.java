package com.gm.wj.dao;

import com.gm.wj.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyList(@Param("ids") List<Integer> id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectBookListLimit(Book book);
}
