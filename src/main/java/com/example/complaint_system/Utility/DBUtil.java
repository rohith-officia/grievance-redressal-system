package com.example.complaint_system.Utility;

public class DBUtil {

    public static final String FIND_COMPLAINT_BY_ID = """
                SELECT 
                c.id,
                c.title,
                c.description,
                c.department,
                c.status,
                c.created_at,
                u.email,
                u.role,
                u.id AS user_id
            FROM CPTS_LST c
            JOIN CPT_STM_USER u ON c.user_email = u.email
            WHERE c.id = ?
        """;

    public static final String FIND_ALL_COMPLAINTS = """
                SELECT
                c.id,
                c.title,
                c.description,
                c.department,
                c.status,
                c.created_at,
                u.email AS user_email,
                u.role AS user_role
            FROM CPTS_LST c
            JOIN CPT_STM_USER u
                ON c.user_email = u.email
            ORDER BY c.created_at DESC
        """;

    public static final String UPDATE_COMPLAINT_STATUS = """
                UPDATE CPTS_LST
                SET
                status = ?
                WHERE id = ?
        """;
}
