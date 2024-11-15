package APIs;

import java.util.List;

/**
 *
 * @author kathe
 * @param <T>
 */
 public class PaginatedResponse<T> {
        private int page;
        private int total_pages;
        private int total_items;
        private List<T> templateAPI; 

        public int getPage() {
            return page;
        }

        public int getTotalPages() {
            return total_pages;
        }

        public int getTotalItems() {
            return total_items;
        }

        public List<T> getItems() {
            return templateAPI;
        }
}
