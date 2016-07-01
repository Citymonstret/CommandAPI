package com.intellectualsites.commands.pagination;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PaginationFactory<T> {

    private List<T> items;
    private int perPage, pageCount;
    private List<Page<T>> pages;

    public PaginationFactory(Class<T> clazz, List<T> items, int perPage) {
        this.items = items;
        this.perPage = perPage;

        this.pageCount = Math.max((int) Math.ceil(items.size() / perPage), 1);
        this.pages = new ArrayList<>(pageCount);

        int index = 0;

        for (int pageNum = 0; pageNum < pageCount; pageNum++) {
            int newIndex = index;

            int ii = 0;
            T[] t = (T[]) Array.newInstance(clazz, perPage);
            for (int i = index; ii < perPage && (i < (index + perPage) || i < items.size()); i++) {
                T tt = items.get(i);
                t[ii++] = tt;
                newIndex = i;
            }
            pages.add(pageNum, new Page<>(t, pageNum));
            index = newIndex + 1;
        }
    }


    public int getPerPage() {
        return perPage;
    }

    public List<Page<T>> getPages() {
        return this.pages;
    }

    public static class Page<Type> {

        private Type[] items;
        private int pageNum;

        protected Page(Type[] items, int pageNum) {
            this.items = items;
            this.pageNum = pageNum;
        }

        public Type[] getItems() {
            return this.items;
        }

        public int getPageNum() {
            return this.pageNum;
        }
    }
}
