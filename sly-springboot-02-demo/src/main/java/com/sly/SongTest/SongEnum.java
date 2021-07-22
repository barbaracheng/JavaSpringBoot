package com.sly.SongTest;

import java.util.Objects;

/**
 * TODO:歌曲对应的枚举
 *
 * @author leyuan
 * @date 2021/7/14 17:06
 */
public enum SongEnum {
    FIRST((byte)1,"十年","十年之前，你不认识我，我不属于你。"),
    SECOND((byte)2,"演员","该配合你演出的我演视而不见。"),
    THIRD((byte)3,"情歌王","你说你，想要走，偏偏注定要分手。"),
    FOURTH((byte)4,"去年夏天","还有什么等待，还有什么期待。"),
    FIFTH((byte)5,"海底","你说你喜欢海风咸咸的气息，踩着湿湿的沙砾。");

    private int id;

    private String songName;

    private String songWord;

    public int getId() {
        return id;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongWord() {
        return songWord;
    }

    SongEnum() {
    }

    SongEnum(int id, String songName, String songWord) {
        this.id = id;
        this.songName = songName;
        this.songWord = songWord;
    }

    /**
     * 功能：根据歌曲名称查找对应歌词
     * 步骤：
     * 1 遍历枚举所有元素
     * 2 判断枚举元素是否跟参数一致
     * 3 一致则返回对应歌词，不一致返回null
     * @param songName
     * @return
     */
    public static String getSongWordByName(String songName) {
        for (SongEnum song : SongEnum.values()) {
            if (Objects.equals(song.songName, songName)) {
                return song.getSongWord();
            }
        }
        return null;
    }
}
