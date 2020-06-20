package ru.otus.hw02.service.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw02.dao.AnswerDao;
import ru.otus.hw02.domain.Answer;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerDao dao;

    @Autowired
    public AnswerServiceImpl(AnswerDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<Answer> getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Answer> getByQuestionId(int id) {
        return dao.getByQuestionId(id);
    }

    @Override
    public List<Answer> getAll() {
        return dao.getAll();
    }
}
