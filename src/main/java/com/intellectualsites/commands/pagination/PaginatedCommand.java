package com.intellectualsites.commands.pagination;

import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandInstance;

public abstract class PaginatedCommand<T> extends Command {

    private PaginationFactory<T> paginationFactory;

    private ItemGetter<T> itemGetter;
    private Class<T> type;
    private int perPage;

    public PaginatedCommand(Class<T> type, ItemGetter<T> itemGetter, int perPge, String command, String usage, String description, String permission, String[] aliases, Class requiredType) {
        super(command, usage, description, permission, aliases, requiredType);
        // paginationFactory = new PaginationFactory<T>(type, itemGetter, perPge);
        this.itemGetter = itemGetter;
        this.type = type;
        this.perPage = perPge;
    }

    public abstract boolean handleTooBigPage(CommandInstance instance, int specifiedPage, int maxPages);

    protected PaginationFactory<T> getPaginationFactory() {
        if (paginationFactory == null) {
            paginationFactory = new PaginationFactory<T>(type, itemGetter.getItems(), perPage);
        }
        return paginationFactory;
    }

    final public boolean onCommand(CommandInstance instance) {
        if (!getRequiredArguments().containsKey("page")) {
            throw new RuntimeException("There is no page argument set :(");
        }
        PaginatedCommandInstance<T> paginatedCommandInstance = new PaginatedCommandInstance<>(instance);

        int page = Math.max(instance.getInteger("page") - 1, 0);
        if (page >= getPaginationFactory().getPages().size()) {
            return handleTooBigPage(instance, page, getPaginationFactory().getPages().size());
        }

        paginatedCommandInstance.setPage(getPaginationFactory().getPages().get(page));

        return onCommand(paginatedCommandInstance);
    }

    public abstract boolean onCommand(PaginatedCommandInstance<T> instance);

    public class PaginatedCommandInstance<T> extends CommandInstance {

        private PaginationFactory.Page<T> page;

        public PaginatedCommandInstance(CommandInstance parent) {
            super(parent.getCaller(), parent.getArguments(), parent.getValueMapping());
        }

        void setPage(final PaginationFactory.Page<T> page) {
            this.page = page;
        }

        public PaginationFactory.Page<T> getPage() {
            return this.page;
        }
    }
}
