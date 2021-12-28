package XmlClassType;

/**
 * RequestEnum 枚举类型的描述
 * @VERSION v1.0
 * @DATE 2021.12.21
 * @author Zhang Haohan,Zhang Yingying
 * 这里面的枚举量是用户所要查询/更新/删除的状态
 */
public enum RequestEnum {
    CLICK_AN_ALBUM, // 点击单个专辑
    CLICK_A_SONGLIST, //点击单个歌单,显示歌单中的歌曲
    CLICK_A_SONG, // 点击单个歌曲
    CLICK_A_USER, // 点击单个用户名/头像
    REGIST_A_USER, //注册一个用户
    LOGIN_A_USER, // 用户登录
    CREATE_A_SONGLIST, // 创建一个歌单
    SHOW_MY_SONGLISTS, //查看我的所有歌单
    CLICK_RANKING_LIST, //点击排行榜
    CLICK_SINGER_LIST, //点击歌手栏目,获取所有歌手
    CREATE_A_ALBUM, //创建一个专辑
    ADD_AN_ALBUM, //添加一个歌单至收藏
    DELETE_AM_ALBUM,//将歌单移出收藏
}
