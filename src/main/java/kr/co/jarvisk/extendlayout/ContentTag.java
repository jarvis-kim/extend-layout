package kr.co.jarvisk.extendlayout;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.Writer;

public class ContentTag extends ImplementTag {

    private static final String CALL_CONTENT_IN_PAGE_ATTR = "kr.co.jarvisk.taglib.layout.CALLED_IN_PAGE";

    public static final String CONTENT_WRITER_ATTR = "kr.co.jarvisk.taglib.layout.CONTENT_WRITER";

    private String extend;

    @Override
    protected void write() throws JspException, IOException {
        if ( isCalledContentTag() ) {
            throw new AlreadyCalledContentException("Content Tag는 페이지에서 한번만 호출 가능합니다");
        }
        changeStatusContentCalled();

        Writer writer = getWriter();
        getJspBody().invoke(writer);

        setContentWriter(writer);
        PageContext pageContext = (PageContext) getJspContext();
        try {
            pageContext.forward(extend);
        } catch (ServletException e) {
            throw new JspException(e);
        }
    }

    private void setContentWriter(Writer contentWriter) {
        getJspContext().setAttribute(CONTENT_WRITER_ATTR, contentWriter, PageContext.REQUEST_SCOPE);
    }

    private void changeStatusContentCalled() {
        getJspContext().setAttribute(CALL_CONTENT_IN_PAGE_ATTR, Boolean.TRUE);
    }

    private boolean isCalledContentTag() {
        Boolean isCalled = (Boolean) getJspContext().getAttribute(CALL_CONTENT_IN_PAGE_ATTR);
        if ( isCalled == null ) {
            return false;
        }

        return isCalled;
    }


    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }
}
