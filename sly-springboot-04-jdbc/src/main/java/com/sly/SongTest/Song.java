package com.sly.SongTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/16 20:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private int id;

    private String songName;

    private String songWord;
}
