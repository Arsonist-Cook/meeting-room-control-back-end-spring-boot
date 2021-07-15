package io.github.arsonistcook.saladereuniao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meetingroom")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "startHour", nullable = false)
    private String startHour;
    @Column(name = "endHour", nullable = false)
    private String endHour;

    @Override
    public String toString() {
        return String.format("Room [ id=%d, name=%s, startHour=%s, endHour=%s ]", id, name, startHour, endHour);
    }

}
