package com.intellectualsites.commands.pagination;

import java.util.List;

public interface ItemGetter<T> {

    List<T> getItems();

}
