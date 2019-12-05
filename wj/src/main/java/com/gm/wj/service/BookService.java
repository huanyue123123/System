package com.gm.wj.service;

import com.gm.wj.dao.BookMapper;
import com.gm.wj.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public Integer saveBook(Book book){
        Integer result = 0;
        if(book.getId() == null){
            //新增
            result = bookMapper.insert(book);
        }else{
            //编辑
            result = bookMapper.updateByPrimaryKeySelective(book);
        }
        return result;
    }

    public Book detail(Integer id){
        Book book = bookMapper.selectByPrimaryKey(id);
        return book;
    }

    public List<Book> bookList(Book book){
        return bookMapper.selectBookListLimit(book);
    }


    public Integer deleteBook(List<Integer> ids){
        if(ids != null){
                return bookMapper.deleteByPrimaryKeyList(ids);
        }
        return 0;
    }

}
