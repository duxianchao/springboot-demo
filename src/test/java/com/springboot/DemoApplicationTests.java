package com.springboot;

import com.google.common.collect.Lists;
import com.springboot.elastic.article.document.Article;
import com.springboot.elastic.article.service.ArticleService;
import com.springboot.elastic.user.document.User;
import com.springboot.elastic.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
        List<Article> byAuthorAndArticleName = articleService.findByAuthorAndArticleName("张三", "论祖国崛起之理论");
        byAuthorAndArticleName.forEach(System.out::println);
        articleService.findByAuthor("zhangsan");
    }
	@Test
	public void userTest() {
        insertIntoES();
            /**
         * 需求
         * 按年龄进行分段统计数据，10以下，10-20，20-30,30-40,40-50,50-60,60以上
         * 并列出18-60岁的人
         * .addUnboundedTo(10)
         .addRange("10岁-20岁以下",10, 20)//包左不包右
         .addRange(20, 30)
         .addRange(30, 40)
         .addRange(40, 50)
         .addUnboundedFrom(50);
         */

        userService.aggRangeByAge();

    }

    /**
     * 录入测试数据
     */
    void insertIntoES(){
        List<User> users = Lists.newArrayList();
        for (int i=0 ;i<10000 ;i++){
            User user = createUser();
            users.add(user);
        }
        userService.save(users);
    }
    public static User createUser(){
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR,1960+random.nextInt(50));
        calendar.set(Calendar.MONTH,random.nextInt(12));
        calendar.set(Calendar.DAY_OF_MONTH,random.nextInt(27)+1);
        calendar.set(Calendar.HOUR_OF_DAY,random.nextInt(24));
        calendar.set(Calendar.MINUTE,random.nextInt(60));
        int birthYear = calendar.get(Calendar.YEAR);

        User user = new User();
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setGender(genders[random.nextInt(1)]);
        StringBuilder stringBuilder = new StringBuilder(11);
        stringBuilder.append("1");
        for(int i=0;i<10;i++){
            stringBuilder.append(random.nextInt(10));
        }
        user.setPhoneNum(stringBuilder.toString());
        user.setAge(nowYear-birthYear);
        String[] userLikes = new String[random.nextInt(6)+2];
        w:for(int i =0 ; i< userLikes.length ;i++){
            String like = likes[random.nextInt(likes.length)];
            for(int j=0; j<i;j++){
                if(like.equals(userLikes[j])){
                    i--;
                    continue w;
                }
            }
            userLikes[i] = like;
        }

        user.setLikes(userLikes);
        user.setAddress(areas[random.nextInt(areas.length)]);
        user.setBirthday(calendar.getTime());
        StringBuilder userName = new StringBuilder();
        for (int i = 0; i <5 ; i++) {
            userName.append(str.charAt(random.nextInt(str.length())));
        }
        user.setUserName(userName.toString());

        StringBuilder realName = new StringBuilder();
        String[] s1 = surname.split("");
        String[] s2 = lastName.split("");
        realName.append(s1[random.nextInt(s1.length)]);
        realName.append(s2[random.nextInt(s2.length)]);
        user.setRealName(realName.toString());
        return user;
    }
    private static String[] genders = {"男","女"};
    private static String[] areas = {"海淀","朝阳","大兴","西城","东城","宣武","怀柔","顺义","石景山","通州","丰台"};
    private static String[] likes = {"篮球","足球","排球","乒乓球",
            "dota","台球","扑克","麻将","LOL","王者荣耀",
            "魔兽","韩剧","美剧","日剧","港台剧","爱情动作片",
            "逛街","吃饭","喝酒","抽烟","跑步","游泳","深蹲","健步走",
            "散步","遛狗","溜猫","打豆豆","睡觉","美容","发呆",
            "跳绳","玩电脑","健美","拳击","跳舞","广场",
            "美女","帅哥","大数","旅游","踏青","钓鱼","养花","瑜伽","有氧运动"};
    private static String str = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    private static String surname = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁锺徐邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄麴家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭历戎祖武符刘景詹束龙叶幸司韶郜黎蓟溥印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阳郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀僪浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄阙东欧殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空";
    private static String lastName = "可以看到这个词和传到查询的原始值是一样的我们不会详细介绍查询以及查询解析器如何构建查询但总地来说分析之后的索引词和分析之后查询词是一样的因此该文档与查询匹配并作为结果返回默认情况下通配符查询和前缀查询不会被分析如果要更改此行为比如正在讨论的这个查询可以区分两种类型的词查询的这一部分不能出现在文档中还可以使用秋萍是我们学校最漂亮很有才学的骨干教师也是个非常可爱很有主见的时代年轻人她原准备去年结婚的那段时间秋萍整天忙得不亦乐乎买房装修置家半年来每一样她都要亲自操心光请柬就写了四百多张累得她膀子像灌了铅她的未婚夫叫留佳一个富有诗情画意的名字他是名牌大学的研究生三年前他放弃政府部门的工作自己办起了公司生意做得红红火火只是整天忙没有多少时间顾及秋萍秋萍很贤惠对未婚夫更多的是关爱信任和理解她知道公司的每一点成绩都要靠留佳在打拼在现在的社会中竞争之激烈是难以想象的一想到留佳和自己未来甜蜜的生活秋萍眼角里始终填满了知足的微笑我们也都替她高兴暗自庆幸他们的天赐良缘可就当秋萍沐浴在婚姻的喜庆之中时发生了一件极小的事情改变了她的初衷也彻底扭转了她的人生那天她和留佳拍完婚纱照回来天色已很晚还下着细雨兴奋又疲惫的她就和留佳去吃风味小吃两人来到一个不大的巷口遇到了红灯一瞧还有61秒的等待留佳见两边没有车子猛然一把拉着她就冲了过去吓得她浑身直起鸡皮疙瘩饭桌上秋萍委婉地批评了他让他珍爱自己的生命可留佳却不以为然还调侃道你真有点神经质是不是当老师时间长了教育人成为了你的习惯哪有那么多的车祸这是留佳第一次顶撞秋萍而且因为这件小事秋萍愕然两人最终不欢而散接到电话的留佳丢下饭桌上的秋萍飘然而去然而风雨中她再一次看见留佳一个箭步又闯了红灯没有想到刚才半天的唠叨他怎么可以依然故我独自回到家的秋萍一下子懵了坐在床上辗转反侧她想到了用手机短信和他交流她把自己的观点和对留佳深深的爱写成一条条短信前前后后发了二十多条始终没有等到留佳的回复一打电话关机了第二天中午她终于盼来了留佳的信息一看只有十三个字：神经质小题大做我没有时间等她顿感无语今年年初秋萍悄而无声地走了听说她去了广州前日留佳还收到她从广州寄来的一封信只有寥寥几句婚姻不是游戏人生更没有彩排一个连自己生命都不懂得爱惜的人我还能指望你用一生一世的情去呵护我和我们的家人吗我主宰不了我们的缘分但我可以主宰我自己的选择记住：平安是最大的福祝您一生平安”随信还夹了两片早已枯黄的枫叶作者简介黄宏宣男中国散文家协会会员中国东方作家创作中心会员江苏省作家协会会员国家三级创作员在各类刊物网站上发表作品千余篇十多篇散文在各级评比中获奖并出版散文我这十和长篇小深深叹";


}
