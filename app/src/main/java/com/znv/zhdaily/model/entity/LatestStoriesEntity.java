package com.znv.zhdaily.model.entity;

import java.util.List;

/**
 * Created by znv on 2017/4/4.
 */

public class LatestStoriesEntity {


    /**
     * date : 20170404
     * stories : [{"images":["https://pic2.zhimg.com/v2-b92dcd4ba1e9b9c15c4a1c04970b50a1.jpg"],"type":0,"id":9334357,"ga_prefix":"040410","title":"小户型没厅不是借口，再小的房子也能挤出家的位置"},{"images":["https://pic3.zhimg.com/v2-7569072c4fda5b4906820a5eb39898fa.jpg"],"type":0,"id":9331477,"ga_prefix":"040409","title":"去美国的监狱看了看，发现和《越狱》完全不一样"},{"images":["https://pic4.zhimg.com/v2-fc094e7c56bde46cc319dd6d5466aaf7.jpg"],"type":0,"id":9334599,"ga_prefix":"040408","title":"解冻食物，你用对方法了吗？"},{"images":["https://pic4.zhimg.com/v2-5858e9472c98db2d49e7035a4238f42f.jpg"],"type":0,"id":9334236,"ga_prefix":"040407","title":"如果你的家（小区）经常被盗，你该怎么办？"},{"images":["https://pic4.zhimg.com/v2-a454dcc39509209742815f4e0b1415ef.jpg"],"type":0,"id":9332988,"ga_prefix":"040407","title":"裁员就裁员，为什么还要找我谈话啊"},{"images":["https://pic4.zhimg.com/v2-9b9dcd4b3778770a39dbc9d45c0a8b07.jpg"],"type":0,"id":9332670,"ga_prefix":"040407","title":"我胆小，孤僻，适应困难，但我没有那么需要被可怜"},{"images":["https://pic2.zhimg.com/v2-0d0e63bf5050db70cc9b5a446fbe28c1.jpg"],"type":0,"id":9334560,"ga_prefix":"040406","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-ed27b07786396de3df5d6c3cf6c75d05.jpg","type":0,"id":9332670,"ga_prefix":"040407","title":"我胆小，孤僻，适应困难，但我没有那么需要被可怜"},{"image":"https://pic2.zhimg.com/v2-9c100252abe543914ba52a81cc2d9791.jpg","type":0,"id":9333546,"ga_prefix":"040314","title":"又拿下一个冠军， 35 岁的费德勒依然在进化"},{"image":"https://pic1.zhimg.com/v2-ffedd43aaa253d845b19846828927ec8.jpg","type":0,"id":9332986,"ga_prefix":"040307","title":"公司又投放了 20 万辆单车，小刘你把账记一下吧"},{"image":"https://pic2.zhimg.com/v2-66b002a8dfeed58e59f043a8de1494cd.jpg","type":0,"id":9332901,"ga_prefix":"040307","title":"「如果你不主动找我，我也不会主动找你」"},{"image":"https://pic2.zhimg.com/v2-6f577ff9f3d1c9d31a90e7a768b9c18d.jpg","type":0,"id":9330011,"ga_prefix":"040210","title":"在现实生活中，我们往往都是认知的吝啬鬼"}]
     */

    private String date;
    /**
     * images : ["https://pic2.zhimg.com/v2-b92dcd4ba1e9b9c15c4a1c04970b50a1.jpg"]
     * type : 0
     * id : 9334357
     * ga_prefix : 040410
     * title : 小户型没厅不是借口，再小的房子也能挤出家的位置
     */

    private List<StoryBean> stories;
    /**
     * image : https://pic2.zhimg.com/v2-ed27b07786396de3df5d6c3cf6c75d05.jpg
     * type : 0
     * id : 9332670
     * ga_prefix : 040407
     * title : 我胆小，孤僻，适应困难，但我没有那么需要被可怜
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoryBean> getStories() {
        return stories;
    }

    public void setStories(List<StoryBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }



    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
