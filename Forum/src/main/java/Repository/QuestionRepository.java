package Repository;

import Model.ForumList;
import Model.Question;

public class QuestionRepository implements Repository<Question>{
    @Override
    public Question findOne(int id) {
        return null;
    }

    @Override
    public Question findAll() {
        return null;
    }

    @Override
    public boolean save(Question entity) {
        return false;
    }

    @Override
    public boolean update(Question entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
