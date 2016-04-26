package kr.co.jarvisk.extendlayout;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.List;

public class LayoutHelper {

    private static final String CONTEXT_HASH_CODES_KEY = "kr.co.jarvisk.taglib.layout.CONTEXT_HASH_CODES";

    static void setDepth(JspContext jspContext) {
        List<Integer> contextHashCodes = (List<Integer>) jspContext.findAttribute(CONTEXT_HASH_CODES_KEY);
        if ( contextHashCodes == null ) {
            contextHashCodes = new ArrayList<Integer>();
            jspContext.setAttribute(CONTEXT_HASH_CODES_KEY, contextHashCodes, PageContext.REQUEST_SCOPE);
        }

        if ( !contextHashCodes.contains(jspContext.hashCode()) ) {
            contextHashCodes.add(jspContext.hashCode());
        }
    }

    static int getDepth(JspContext jspContext) {
        List<Integer> contextHashCodes = (List<Integer>) jspContext.findAttribute(CONTEXT_HASH_CODES_KEY);
        if ( contextHashCodes == null ) {
            contextHashCodes = new ArrayList<Integer>();
            jspContext.setAttribute(CONTEXT_HASH_CODES_KEY, contextHashCodes, PageContext.REQUEST_SCOPE);
        }
        return contextHashCodes.indexOf(jspContext.hashCode());
    }

}
