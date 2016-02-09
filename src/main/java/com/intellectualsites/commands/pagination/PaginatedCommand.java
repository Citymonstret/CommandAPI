package com.intellectualsites.commands.pagination;

import com.intellectualsites.commands.Command;
import com.intellectualsites.commands.CommandInstance;

import java.util.List;

public abstract class PaginatedCommand<T> extends Command {

    private PaginationFactory<T> paginationFactory;

    public PaginatedCommand(Class<T> type, List<T> items, int perPge, String command, String usage, String description, String permission, String[] aliases, Class requiredType) {
        super(command, usage, description, permission, aliases, requiredType);
        paginationFactory = new PaginationFactory<T>(type, items, perPge);
    }

    public abstract boolean handleTooBigPage(CommandInstance instance, int specifiedPage, int maxPages);

    final public boolean onCommand(CommandInstance instance) {
        if (!getRequiredArguments().containsKey("page")) {
            throw new RuntimeException("There is no page argument set :(");
        }
        PaginatedCommandInstance paginatedCommandInstance = new PaginatedCommandInstance(instance);

        int page = Math.max(instance.getInteger("page") - 1, 0);
        if (page >= paginationFactory.getPages().size()) {
            return handleTooBigPage(instance, page, paginationFactory.getPages().size());
        }

        paginatedCommandInstance.setPage(paginationFactory.getPages().get(page));

        return onCommand(paginatedCommandInstance);
    }

    public abstract boolean onCommand(PaginatedCommandInstance instance);

    public class PaginatedCommandInstance extends CommandInstance {

        private PaginationFactory.Page page;

        public PaginatedCommandInstance(CommandInstance parent) {
            super(parent.getCaller(), parent.getArguments(), parent.getValueMapping());
        }

        void setPage(final PaginationFactory.Page page) {
            this.page = page;
        }

        public PaginationFactory.Page getPage() {
            return this.page;
        }
    }
}
