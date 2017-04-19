package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.GET_ALL, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.BETWEEN, query = "SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC "),
        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.description=:description, m.calories=:calories, m.dateTime=:dateTime WHERE m.id=:id AND m.user.id=:userId"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"date_time", "user_id"})})
public class Meal extends BaseEntity {

    public static final String UPDATE = "Meal.update";
    public static final String DELETE = "Meal.delete";
    public static final String GET = "Meal.get";
    public static final String GET_ALL = "Meal.get_all";
    public static final String BETWEEN = "Meal.between";

    @Column(name = "date_time", nullable = false)
    @NotBlank(groups = LocalDateTime.class, message = "Date must be no empty")
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotNull
    private String description;

    @Column(name = "calories", nullable = false)
    @NotBlank(groups = Integer.class, message = "Date must be no empty")
    @Range(min = 10, max = 10000, message = "CALORIES in Meal must be min = 10 max = 10000")
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
