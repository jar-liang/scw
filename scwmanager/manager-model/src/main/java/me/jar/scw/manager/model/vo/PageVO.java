package me.jar.scw.manager.model.vo;

import java.util.List;

/**
 * @Description 用来分页，默认显示5页
 * @Date 2020/3/12-23:36
 */
public class PageVO<T> {
    //已知的参数
    //当前页,从请求那边传过来
    private int pageNum;
    //每页显示的数据条数
    private int pageSize;
    //总的记录条数。查询数据库得到的数据
    private int totalRecord;

    // 用来判断上一页和下一页
    private boolean hasPrePage;
    private boolean hasNextPage;

    // 保存页码数
    private int[] showPages;

    //需要计算得来
    //总页数，通过totalRecord和pageSize计算可以得来
    private int totalPage;
    //开始索引，也就是我们在数据库中要从第几行数据开始拿，有了startIndex和pageSize，
    //就知道了limit语句的两个数据，就能获得每页需要显示的数据了
    private int startIndex;
    //将每页要显示的数据放在list集合中
    private List<T> list;
    //分页显示的页数,比如在页面上显示1，2，3，4，5页，start就为1，end就为5，这个也是算过来的
    private int start;
    private int end;
    //通过pageNum，pageSize，totalRecord计算得来tatalPage和startIndex
    //构造方法中将pageNum，pageSize，totalRecord获得
    public PageVO(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        //totalPage 总页数
        if(totalRecord%pageSize==0){
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            this.totalPage = totalRecord / pageSize;
        }else{
            //不整除，就要在加一页，来显示多余的数据。
            this.totalPage = totalRecord / pageSize +1;
        }
        //开始索引
        this.startIndex = (pageNum-1)*pageSize ;
        //显示5页，这里自己可以设置，想显示几页就自己通过下面算法修改
        this.start = 1;
        this.end = 5;
        //显示页数的算法

        if(totalPage <=5){
            //总页数都小于5，那么end就为总页数的值了。
            this.end = this.totalPage;
        }else{
            //总页数大于5，那么就要根据当前是第几页，来判断start和end为多少了，
            this.start = pageNum - 2;
            this.end = pageNum + 2;

            if(start < 0){
                //比如当前页是第1页，或者第2页，那么就不如和这个规则，
                this.start = 1;
                this.end = 5;
            }
            if(end > this.totalPage){
                //比如当前页是倒数第2页或者最后一页，也同样不符合上面这个规则
                this.end = totalPage;
                this.start = end - 5;
            }
        }
        // 判断是否有上一页和下一页
        if (pageNum == 1) {
            this.hasPrePage = false;
            if (this.end > 1) {
                this.hasNextPage = true;
            } else {
                this.hasNextPage = false;
            }
        } else {
            this.hasPrePage = true;
            if (pageNum == this.end) {
                this.hasNextPage = false;
            } else {
                this.hasNextPage = true;
            }
        }
        // 将显示的页数装进数组中
        int length = end - start + 1;
        showPages = new int[length];
        showPages[0] = start;
        for (int i = 1; i < length; i++) {
            showPages[i] = showPages[i -1] + 1;
        }
    }
    //get、set方法。
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int[] getShowPages() {
        return showPages;
    }

    public void setShowPages(int[] showPages) {
        this.showPages = showPages;
    }
}
