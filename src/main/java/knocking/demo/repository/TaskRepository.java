package knocking.demo.repository;

import knocking.demo.task.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @date 2016/9/20 17:04
 **/
@Repository
public interface TaskRepository extends CrudRepository<Task, String> {

    Task save(final Task task);

    void delete(String payload);

    Task findOne(String payload);

    List<Task> findByPayload(String payload);

    Iterable<Task> findAll();

//    public long createTask(final Task task) {
//
//        KeyHolder holder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(new PreparedStatementCreator() {
//
//            @Override
//            public PreparedStatement createPreparedStatement(
//                    Connection connection) throws SQLException {
//                PreparedStatement ps = connection
//                        .prepareStatement("INSERT INTO tasks (payload, updated" +
//                                        ") VALUES(?, NOW())",
//                                Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, task.getPayload());
//                return ps;
//            }
//        }, holder);
//
//        return holder.getKey().longValue();
//    }
}
