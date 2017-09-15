/** Generated by BaggageBuffersCompiler */
package brown.tracingplane.bdl.examples;

import brown.tracingplane.bdl.Struct;
import brown.tracingplane.bdl.StructHelpers;
import brown.tracingplane.bdl.examples.SimpleStruct1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import brown.tracingplane.bdl.BDLUtils;
import java.nio.ByteBuffer;


public class SimpleStruct2 implements Struct {

    private static final Logger _log = LoggerFactory.getLogger(SimpleStruct2.class);

    public Long integerField = 0L;
    public SimpleStruct1 nestedStruct = new SimpleStruct1();

    private static final SimpleStruct2 _defaultValue = new SimpleStruct2();
    private static final Long _integerField_defaultValue = 0L;
    private static final SimpleStruct1 _nestedStruct_defaultValue = new SimpleStruct1();

    @Override
    public Struct.StructHandler<?> handler() {
        return Handler.instance;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("SimpleStruct2{\n");
            b.append(BDLUtils.indent(String.format("integerField = %s\n", String.valueOf(this.integerField == null ? _integerField_defaultValue : this.integerField))));
            b.append(BDLUtils.indent(String.format("nestedStruct = %s\n", String.valueOf(this.nestedStruct == null ? _nestedStruct_defaultValue : this.nestedStruct))));
            b.append("}");
        return b.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return SimpleStruct2.equals(this, _defaultValue);
        } else if (!(other instanceof SimpleStruct2)) {
            return false;
        } else {
            return SimpleStruct2.equals(this, (SimpleStruct2) other);
        }
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 37 + (this.integerField == null ? _integerField_defaultValue : this.integerField).hashCode();
        result = result * 37 + (this.nestedStruct == null ? _nestedStruct_defaultValue : this.nestedStruct).hashCode();
        return result;
    }

    private static boolean equals(SimpleStruct2 a, SimpleStruct2 b) {
        if (!BDLUtils.equals(a.integerField, b.integerField, _integerField_defaultValue)) return false;
        if (!BDLUtils.equals(a.nestedStruct, b.nestedStruct, _nestedStruct_defaultValue)) return false;
        return true;
    }

    public static class Handler implements Struct.StructHandler<SimpleStruct2> {

        public static final Handler instance = new Handler();

        private Handler(){}


        private static final Struct.StructReader<Long> _integerFieldReader = StructHelpers.int64Reader;
        private static final Struct.StructSizer<Long> _integerFieldSizer = StructHelpers.int64Sizer;
        private static final Struct.StructWriter<Long> _integerFieldWriter = StructHelpers.int64Writer;

        private static final Struct.StructHandler<SimpleStruct1> _nestedStructHandler = SimpleStruct1.Handler.instance;

        @Override
        public SimpleStruct2 readFrom(ByteBuffer buf) throws Exception {
            SimpleStruct2 instance = new SimpleStruct2();

            try {
                instance.integerField = _integerFieldReader.readFrom(buf);
                instance.nestedStruct = _nestedStructHandler.readFrom(buf);
            } catch (Exception e) {
                _log.warn("Exception parsing SimpleStruct2 ", e);
            }

            return instance;
        }

        @Override
        public void writeTo(ByteBuffer buf, SimpleStruct2 instance) {
            try {
                _integerFieldWriter.writeTo(buf, instance.integerField == null ? _integerField_defaultValue : instance.integerField);
                _nestedStructHandler.writeTo(buf, instance.nestedStruct == null ? _nestedStruct_defaultValue : instance.nestedStruct);
            } catch (Exception e) {
                _log.warn("Exception serializing SimpleStruct2 ", e);
            }
        }

        @Override
        public int serializedSize(SimpleStruct2 instance) {
            int size = 0;
            size += _integerFieldSizer.serializedSize(instance.integerField == null ? _integerField_defaultValue : instance.integerField);
            size += _nestedStructHandler.serializedSize(instance.nestedStruct == null ? _nestedStruct_defaultValue : instance.nestedStruct);
            return size;
        }
    }
}