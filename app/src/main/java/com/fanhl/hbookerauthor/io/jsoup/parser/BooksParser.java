package com.fanhl.hbookerauthor.io.jsoup.parser;

import com.fanhl.hbookerauthor.data.Book;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
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
            //<td><input type="checkbox" class="subSelect" data-id="100028379"></td>
            Element checkbox = tds.get(0);
            String bookId = checkbox.select("input").get(0).attr("data-id");

            //<td><a href="http://www.hbooker.com/book/book_detail?book_id=xxxxx" target="_blank">
            //		<div class="img_wrap">
            //		    <img  class="img" src="https://novel-cdn.kuangxiangit.com/uploads/allimg/xxxxx/xxxxx.jpg" height="116" width="auto">
            //      </div>
            //	</a>
            //	<span class="collect">总收藏 2</span>
            //</td>
            Element cover = tds.get(1);
//            String bookUrl = cover.select("a").get(0).attr("href");
            String coverImgUrl = cover.select("img").get(0).attr("src");
            String collectStr = cover.select("span").get(0).html();
            int collect = Integer.valueOf(collectStr.split(" ")[1]);

            Book item = new Book();
            item.setId(bookId);
            item.setCover(coverImgUrl);
            item.setCollect(collect);

            list.add(item);
        }

        return list;
    }
}
