package com.example.zhangzk.testmaintab;


/**
 * 主框架tab相关,增减tab见{@link #sAllTabs}；tab信息结构见{@link #Tab}；生成tab对应的fragment实例见
 * {@link #FragmentBuilder}
 */
public class MainTabSpec {

    public static final String TAG_INDEX = "index"; // 首页
    public static final String TAG_SQUARE = "square"; // 广场
    public static final String TAG_HOME = "home"; // 小区宝
    public static final String TAG_SHOPMALL = "shopmall"; // 商城
    public static final String TAG_USER = "user"; // 用户

    /** 需要展示的tab，从左到右的顺序 */
    public static final Tab sAllTabs[] = {
            Tab.INDEX, Tab.QUARE,
//            Tab.HOME,
             Tab.SHOPMALL, Tab.USER
    };

    /**
     * tab枚举，内含tag，图标和标题
     */
    public static enum Tab {
    	INDEX(TAG_INDEX),
    	QUARE(TAG_SQUARE),
//    	HOME(TAG_HOME),
    	SHOPMALL(TAG_SHOPMALL),
        USER(TAG_USER);
    	
        Tab(String tag) {
            this.mTag = tag;
            if (tag.equals(TAG_INDEX)) {
                mIconRes = R.drawable.main_tab_index_selector;
                mTextRes = R.string.main_tab_index;
            } else if (tag.equals(TAG_SQUARE)) {
                mIconRes = R.drawable.main_tab_square_selector;
                mTextRes = R.string.main_tab_square;
            } else if (tag.equals(TAG_SHOPMALL)) {
                mIconRes = R.drawable.main_tab_shopmall_selector;
                mTextRes = R.string.main_tab_shopmall;
            } else if (tag.equals(TAG_USER)) {
                mIconRes = R.drawable.main_tab_user_selector;
                mTextRes = R.string.main_tab_user;
            }
        }

        private String mTag;
        /** 图标资源  */
        private int mIconRes;
        /** 标题资源  */
        private int mTextRes;

        /**
         * @return tab对应的tag
         */
        public String getTag() {
            return mTag;
        }

        /**
         * @return tab对应的图标资源
         */
        public int getIcon() {
            return mIconRes;
        }

        /**
         * @return tab对应的标题资源
         */
        public int getText() {
            return mTextRes;
        }
    }
}
