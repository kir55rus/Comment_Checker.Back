package pro.batalin.commentchecker.rest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

public class PaginationUtils {
    private static final Logger logger = LoggerFactory.getLogger(PaginationUtils.class);

    public static final String HEADER_TOTAL_COUNT = "X-Total-Count";
    public static final String HEADER_TOTAL_PAGES = "X-Total-Pages";
    public static final String HEADER_PAGE_SIZE = "X-Page-Size";
    public static final String HEADER_CURRENT_PAGE = "X-Current-Page";

    public static HttpHeaders generatePaginationHttpHeaders(String baseUrl, Page<?> page, Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_TOTAL_COUNT, "" + page.getTotalElements());
        headers.add(HEADER_TOTAL_PAGES, "" + page.getTotalPages());
        headers.add(HEADER_PAGE_SIZE, "" + pageable.getPageSize());
        headers.add(HEADER_CURRENT_PAGE, "" + pageable.getPageNumber());
        try {
            String link = ("<" + getPageUrl(baseUrl, pageable) + ">; rel=\"self\",");
            if (page.nextPageable() != null) {
                link += "<" + getPageUrl(baseUrl, page.nextPageable()) + ">; rel=\"next\",";
            }
            if (page.previousPageable() != null) {
                link += "<" + getPageUrl(baseUrl, page.previousPageable()) + ">; rel=\"prev\",";
            }
            headers.add(HttpHeaders.LINK, link);
        } catch (URISyntaxException e) {
            logger.error("", e);
        }
        return headers;
    }

    private static String getPageUrl(String baseUrl, int pageNumber, int pageSize) throws URISyntaxException {
        return (new URI(baseUrl + "?page=" + pageNumber + "&size=" + pageSize)).toString();
    }
    private static String getPageUrl(String baseUrl, Pageable pageable) throws URISyntaxException {
        return getPageUrl(baseUrl, pageable.getPageNumber(), pageable.getPageSize());
    }
}
