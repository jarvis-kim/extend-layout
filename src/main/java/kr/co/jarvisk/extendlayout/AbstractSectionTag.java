package kr.co.jarvisk.extendlayout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.Writer;

public class AbstractSectionTag extends AbstractTag {

    private boolean ignore = false;

    private String name;

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Class<? extends ImplementTag> getImplementClass() {
        return SectionTag.class;
    }

    @Override
    protected Writer getChildWriter() {
        PageContext pageContext = (PageContext) getJspContext();
        Writer writer = (Writer) pageContext.findAttribute(SectionTag.getSectionWriterKey(getDepth() - 1,name));

        return writer;
    }

    @Override
    protected void renderChild() throws JspException, IOException {
        Writer childWriter = getChildWriter();
        if ( childWriter == null && !isIgnore() ) {
            throw new LayoutException("자식 컨텐츠가 없습니다.[name=" + name + ", depth=" + getDepth() + "]");
        }

        Writer writer = getWriter();
        if ( childWriter != null ) {
            writer.write(childWriter.toString());
        }
        removeChildWriter();
    }

    public void removeChildWriter() {
        getJspContext().setAttribute(SectionTag.getSectionWriterKey(getDepth() - 1, name), null, PageContext.REQUEST_SCOPE);
    }
}
