package com.fanhl.hbookerauthor.io.jsoup.parser;

import com.fanhl.hbookerauthor.data.Book;
import com.fanhl.hbookerauthor.util.DateUtil;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanhl on 2017/4/11.
 */

public class BooksParser {
    public static final String TAG = BooksParser.class.getSimpleName();

    public static List<Book> view_list(Document document) throws IOException {
        Elements tables = document.select("table");
        if (tables == null || tables.isEmpty()) {
            throw new IOException("未找到table");
        }

        Element table = tables.get(0);
        Elements trs = table.select("tbody").get(0).select("tr");

        List<Book> list = new ArrayList<>();

        for (Element tr : trs) {
            Elements tds = tr.select("td");

            if (tds.size() < 8) {
                throw new IOException("列识别失败");
            }
            //<td><input type="checkbox" class="subSelect" data-id="2333333"></td>
            Element checkboxEle = tds.get(0);
            String bookId = checkboxEle.select("input").get(0).attr("data-id");

            //<td><a href="http://www.hbooker.com/book/book_detail?book_id=xxxxx" target="_blank">
            //		<div class="img_wrap">
            //		    <img  class="img" src="https://novel-cdn.kuangxiangit.com/uploads/allimg/xxxxx/xxxxx.jpg" height="116" width="auto">
            //      </div>
            //	</a>
            //	<span class="collect">总收藏 2</span>
            //</td>
            Element coverEle = tds.get(1);
//            String bookUrl = cover.select("a").get(0).attr("href");
            String coverImgUrl = coverEle.select("img").get(0).attr("src");
            int collect = getCollect(coverEle);
            //<td><a href="http://www.hbooker.com/book/book_detail?book_id=2333333" target="_blank">标题标题</a></td>
            Element bookNameEle = tds.get(2);
            String title = bookNameEle.select("a").get(0).html();
            //<td>编辑（QQ: 233333333333）</td>
            String editor = tds.get(3).html();// FIXME: 2017/4/14 这里再建一层 javaBean??
            //<td>4139</td>
            int wordCount = Integer.valueOf(tds.get(4).html());
            //<td>002.name<br/><span class="lighter-grey">(2017-02-25 12:34:56)</span></td>
            Element latestChapterEle = tds.get(5);
            Book.Chapter latestChapter = parseChapter(latestChapterEle.text());
            //<td>未满足条件</td>
            Element statusEle = tds.get(6);
            Book.Status status = Book.Status.parse(statusEle.html());
            //<td>
            //	<ol class="ol-operate opento">
            //		<li><a href="http://author.hbooker.com/book_manage/mod_book_info?book_id=233333&page=1" class="btn rim-btn-success operate-btn url-forward"><i class="icon-settings"></i>作品设置</a></li>
            //		<li><a href="http://author.hbooker.com/book_manage/draft?book_id=233333&page=1" class="btn rim-btn-success operate-btn url-forward"><i class="icon-upload"></i>上传章节</a></li>
            //		<li><a href="http://author.hbooker.com/book_manage/view_chapter_info?book_id=233333&page=1" class="btn rim-btn-success operate-btn url-forward"><i class="icon-text"></i>查看章节</a></li>
            //	</ol>
            //	<ol class="ol-operate">
            //										<li><a href="javascript:;" isEnbale="lock" class="btn rim-btn-default operate-btn applyfor-signed" book_id="233333" urlpage="1"><i class="icon-jewel"></i>申请签约</a></li>
            //		<li><a href="javascript:;" isEnbale="lock" class="btn rim-btn-default operate-btn applyfor-recommend" book_id="233333" urlpage="1" bn="名字"><i class="icon-praise"></i>申请推荐</a></li>
            //		<li><a href="javascript:;" isEnbale="lock" class="btn rim-btn-default operate-btn applyfor-putaway" book_id="233333" urlpage="1" bn="名字"><i class="icon-crown"></i>申请上架</a></li>
            //	</ol>
            //</td>
            Element operatorEle = tds.get(7);//管理操作

            Book item = new Book();
            item.setId(bookId);
            item.setCover(coverImgUrl);
            item.setCollect(collect);
            item.setTitle(title);
            //FIXME editor
            item.setWordCount(wordCount);
            item.setLatestChapter(latestChapter);
            item.setStatus(status);
            //FIXME operator

            list.add(item);
        }

        return list;
    }

    private static Book.Chapter parseChapter(String text) {
        int i = text.indexOf(' ');

        if (i < 0 || i > text.length() - 1) {
            return null;
        }

        String title = text.substring(0, i);
        String timeWrap = text.substring(i + 1, text.length());
        String timeStr = timeWrap.replaceAll("[\\\\(\\\\)]", "");
        Date date = DateUtil.long2date(timeStr);

        Book.Chapter chapter = new Book.Chapter();
        chapter.setTitle(title);
        chapter.setDate(date);
        return chapter;
    }

    private static int getCollect(Element cover) {
        String collectStr = cover.select("span.collect").get(0).html();
        return Integer.valueOf(collectStr.split(" ")[1]);
    }
}
