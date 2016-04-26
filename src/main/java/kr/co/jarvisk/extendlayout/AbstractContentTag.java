package kr.co.jarvisk.extendlayout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.Writer;

public class AbstractContentTag extends AbstractTag {

    @Override
    protected void renderChild() throws JspException, IOException {
        Writer writer = getWriter();

        Writer childWriter = getChildWriter();

        if ( childWriter == null ) {
            throw new LayoutException("자식 컨텐츠가 없습니다.");
        }

        writer.write(childWriter.toString());
    }


    @Override
    protected Class<? extends ImplementTag> getImplementClass() {
        return ContentTag.class;
    }

    public Writer getChildWriter() {
        PageContext pageContext = (PageContext) getJspContext();
        Writer writer = (Writer) pageContext.findAttribute(ContentTag.CONTENT_WRITER_ATTR);

        return writer;
    }

}
