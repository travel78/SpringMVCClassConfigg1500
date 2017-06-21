package ua.com.owu.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String blogTitle;
    private String blogDescription;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "blog")
    private List<Post> posts;
    private String picture;




}
