package com.example.final_project.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "booking")
@Data
@XmlRootElement
//@NamedQueries({
//        @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Booking r")
//        , @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Booking r WHERE r.id = :id")
//        , @NamedQuery(name = "Reservation.findByIdRoom", query = "SELECT r FROM Booking r WHERE r.room = :room")
//        , @NamedQuery(name = "Reservation.findByCheckInDate", query = "SELECT r FROM Booking r WHERE r.checkInDate = :checkInDate")
//        , @NamedQuery(name = "Reservation.findByCheckOutDate", query = "SELECT r FROM Booking r WHERE r.checkOutDate = :checkOutDate")
//        , @NamedQuery(name = "Reservation.findByFullName", query = "SELECT r FROM Booking r WHERE r.fullName = :fullName")
//        , @NamedQuery(name = "Reservation.findByPhone", query = "SELECT r FROM Booking r WHERE r.phone = :phone")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "check_in_date")
    @Temporal(TemporalType.DATE)
    private Date checkInDate;

    @Column(name = "check_out_date")
    @Temporal(TemporalType.DATE)
    private Date checkOutDate;

    @Size(max = 25)
    @Column(name = "full_name")
    private String fullName;

    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;

    @Lob
    @Size(max = 65535)
    @Column(name = "special_request")
    private String specialRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "file_name")
    private String filename;
}