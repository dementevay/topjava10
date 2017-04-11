package ru.javawebinar.topjava.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by andrejdementev on 11.04.17.
 */
public class MealRowMapper implements RowMapper<Meal> {
    private  Integer id = 0;
    private  LocalDateTime dateTime = null;
    private  String description = null;
    private  int calories = 0;

    @Override
    public Meal mapRow(ResultSet resultSet, int i) throws SQLException {

            id = resultSet.getInt(1);

            LocalDate localDate = resultSet.getDate(3).toLocalDate();
            LocalTime localTime = resultSet.getTime(3).toLocalTime();
            dateTime = LocalDateTime.of(localDate, localTime);

            description = resultSet.getString(4);

            calories = resultSet.getInt(5);

        return new Meal(id, dateTime, description, calories);
    }
}
