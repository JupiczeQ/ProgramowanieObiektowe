package Database;

import Models.Category;
import Models.Task;
import Models.TaskPriority;
import Models.TaskStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    public static boolean saveTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, priority, status, user_id, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getPriority().getDisplayName());
            pstmt.setString(4, task.getStatus().getDisplayName());
            pstmt.setInt(5, task.getUserID());
            pstmt.setTimestamp(6, task.getCreatedAt());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static List<Task> getTasksByUserId(int userId) throws SQLException {
        return getTasksByUserId(userId, null, null);
    }

    public static List<Task> getTasksByUserId(int userID, TaskStatus statusFilter, TaskPriority priorityFilter) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT t.id, t.title, t.description, t.priority, t.status, t.category_id, t.user_id, t.created_at, " +
                        "c.name as category_name, c.color as category_color " +
                        "FROM tasks t LEFT JOIN categories c ON t.category_id = c.id " +
                        "WHERE t.user_id = ?"
        );

        if (statusFilter != null) {
            sql.append(" AND t.status = ?");
        }
        if (priorityFilter != null) {
            sql.append(" AND t.priority = ?");
        }

        sql.append(" ORDER BY t.created_at DESC");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            pstmt.setInt(paramIndex++, userID);

            if (statusFilter != null) {
                pstmt.setString(paramIndex++, statusFilter.getDisplayName());
            }
            if (priorityFilter != null) {
                pstmt.setString(paramIndex++, priorityFilter.getDisplayName());
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(TaskPriority.fromDisplayName(rs.getString("priority")));
                task.setStatus(TaskStatus.fromDisplayName(rs.getString("status")));
                task.setUserID(rs.getInt("user_id"));
                task.setCreatedAt(rs.getTimestamp("created_at"));

                int categoryId = rs.getInt("category_id");
                if (!rs.wasNull()) {
                    Category category = new Category(
                            categoryId,
                            rs.getString("category_name"),
                            rs.getString("category_color"),
                            userID
                    );
                    task.setCategory(category);
                }

                tasks.add(task);
            }
        }

        return tasks;
    }

}
