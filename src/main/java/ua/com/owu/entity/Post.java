package ua.com.owu.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String postTitle;
    private String postText;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

}
