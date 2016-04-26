package kr.co.jarvisk.extendlayout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.Writer;

public class SectionTag extends ImplementTag {

    public static final String SECTION_WRITER_ATTR = "kr.co.jarvisk.taglib.layout.SECTION_WRITER-";

    private static final String SECTION_ATTRIBUTE_KEY = "kr.co.jarvisk.taglib.layout.JR_LAYOUT_SECTION-";

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void write() throws JspException, IOException {
        if ( hasSection() ) {
            throw new LayoutException("이미 존재하는 섹션 이름입니다.[" + name + "]");
        }
        setSection();

        Writer writer = getWriter();
        getJspBody().invoke(writer);
        setSectionWriter(writer);
    }

    protected void setSection() {
        getJspContext().setAttribute(SECTION_ATTRIBUTE_KEY + name,  Boolean.TRUE);
    }

    protected void setSectionWriter(Writer sectionWriter) {
        getJspContext().setAttribute(getSectionWriterKey(getDepth(), name), sectionWriter, PageContext.REQUEST_SCOPE);
    }

    static String getSectionWriterKey(int depth, String sectionName) {
        return SECTION_WRITER_ATTR + depth + "_" + sectionName;
    }

    protected boolean hasSection() {

        Boolean hasSection = (Boolean) getJspContext().getAttribute(SECTION_ATTRIBUTE_KEY + name);
        if ( hasSection == null ) {
            return false;
        }
        return hasSection;
    }

}
