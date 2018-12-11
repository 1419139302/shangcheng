package com.zzy.shopp.app.config;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zzy.shopp.app.exception.CommonErrorCode;
import com.zzy.shopp.app.exception.ErrorCode;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class JsonData<T> {

    private final static Integer CODE_SUCCESS = 200;
    private final static String msgSuccess = "success";

    public final static Integer CODE_FAILURE = -1;

    private Integer code;
    private String msg;
    private T data;
    private Long timestamp;


    public JsonData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        timestamp = System.currentTimeMillis();
    }

    public JsonData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        timestamp = System.currentTimeMillis();
    }

    public static <T> JsonData<T> ok(T data) {
        return new JsonData<>(CODE_SUCCESS, msgSuccess, data);
    }

    public static <T> JsonData ok(List<T> data) {
        if (data instanceof Page) {
            Map<String, Object> map = new HashMap();

            List<T> list = new ArrayList<T>();
            for (T object : data) {
                list.add(object);
            }
            map.put("list", list);
            PageInfo info = new PageInfo(data);
            map.put("total", info.getTotal());
            map.put("pages", info.getPages());
            map.put("size", info.getSize());
            map.put("pageSize", info.getPageSize());
            map.put("pageNum", info.getPageNum());
            pageMath(map, info);
            return new JsonData(CODE_SUCCESS, msgSuccess, map);
        } else {
            return new JsonData(CODE_SUCCESS, msgSuccess, data);
        }
    }

    public static <T> JsonData ok(List<T> data, boolean isPage) {
        if (isPage) {
            //TODO
        }
        return new JsonData<>(CODE_SUCCESS, msgSuccess, data);
    }

    public static JsonData ok() {
        return new JsonData(CODE_SUCCESS, msgSuccess);
    }

    public static JsonData error(ErrorCode errorCode) {
        return new JsonData(errorCode.getCode(), errorCode.getErrorMsg());
    }

    public static JsonData error() {
        return error(CommonErrorCode.INNER_ERROR);
    }

    public static JsonData error(Integer errorCode, String msg) {
        return new JsonData(errorCode, msg);
    }

    private static void pageMath(Map map, PageInfo info) {
        map.put("hasPrevious", info.getPageNum() == 1 ? false : true);
        map.put("hasNext", info.getPageNum() == info.getPages() ? false : true);
        map.put("first", info.getPageNum() == 1 ? true : false);
        map.put("last", info.getPageNum() == info.getPages() ? true : false);
        //中间页
        int navigatepageNums[];
        int totalPages = info.getPages();
        int num = info.getPageNum();
        //当总页数小于或等于导航页码(5)数时
        if (totalPages <= 5) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码(5)数时
            navigatepageNums = new int[5];
            int startNum = num - 5 / 2;
            int endNum = num + 5 / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < 5; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > totalPages) {
                endNum = totalPages;
                //最后navigatePages页
                for (int i = 5 - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < 5; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        map.put("navigatepageNums", navigatepageNums);
    }
}
