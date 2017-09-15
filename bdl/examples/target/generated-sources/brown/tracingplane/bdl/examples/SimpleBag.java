/** Generated by BaggageBuffersCompiler */
package brown.tracingplane.bdl.examples;

import brown.tracingplane.ActiveBaggage;
import brown.tracingplane.BaggageContext;
import brown.tracingplane.baggageprotocol.BagKey;
import brown.tracingplane.baggageprotocol.BaggageReader;
import brown.tracingplane.baggageprotocol.BaggageWriter;
import brown.tracingplane.bdl.BDLUtils;
import brown.tracingplane.bdl.Bag;
import brown.tracingplane.bdl.BaggageHandler;
import brown.tracingplane.bdl.Brancher;
import brown.tracingplane.bdl.Branchers;
import brown.tracingplane.bdl.Joiner;
import brown.tracingplane.bdl.Joiners;
import brown.tracingplane.bdl.Parser;
import brown.tracingplane.bdl.Parsers;
import brown.tracingplane.bdl.Serializer;
import brown.tracingplane.bdl.Serializers;
import brown.tracingplane.impl.BDLContextProvider;
import brown.tracingplane.impl.BaggageHandlerRegistry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleBag implements Bag {

    private static final Logger _log = LoggerFactory.getLogger(SimpleBag.class);

    public Set<Long> ids = null;

    public boolean _overflow = false;

    /**
    * <p>
    * Get the {@link SimpleBag} set in the active {@link BaggageContext} carried by the current thread. If no baggage is being
    * carried by the current thread, or if there is no SimpleBag in it, then this method returns {@code null}.
    * </p>
    *
    * <p>
    * To get SimpleBag from a specific Baggage instance, use {@link #getFrom(BaggageContext)}.
    * </p>
    *
    * @return the SimpleBag being carried in the {@link BaggageContext} of the current thread, or {@code null}
    *         if none is being carried. The returned instance maybe be modified and modifications will be reflected in
    *         the baggage.
    */
    public static SimpleBag get() {
        Bag bag = BDLContextProvider.get(ActiveBaggage.peek(), Handler.registration());
        if (bag instanceof SimpleBag) {
            return (SimpleBag) bag;
        } else {
            return null;
        }
    }

    /**
    * <p>
    * Get the {@link SimpleBag} set in {@code baggage}. If {@code baggage} has no SimpleBag set then
    * this method returns null.
    * </p>
    *
    * <p>
    * This method does <b>not</b> affect the Baggage being carried by the current thread.  To get SimpleBag
    * from the current thread's Baggage, use {@link #get()}.
    * </p>
    *
    * @param baggage A baggage instance to get the {@link SimpleBag} from
    * @return the {@link SimpleBag} instance being carried in {@code baggage}, or {@code null} if none is being carried.
    *         The returned instance can be modified, and modifications will be reflected in the baggage.
    */
    public static SimpleBag getFrom(BaggageContext baggage) {
        Bag bag = BDLContextProvider.get(baggage, Handler.registration());
        if (bag instanceof SimpleBag) {
            return (SimpleBag) bag;
        } else if (bag != null) {
            Handler.checkRegistration();
        }
        return null;
    }

    /**
    * <p>
    * Update the {@link SimpleBag} set in the current thread's baggage. This method will overwrite any existing
    * SimpleBag set in the current thread's baggage.
    * </p>
    *
    * <p>
    * To set the {@link SimpleBag} in a specific {@link BaggageContext} instance, use
    * {@link #setIn(BaggageContext, SimpleBag)}
    * </p>
    *
    * @param simpleBag the new {@link SimpleBag} to set in the current thread's {@link BaggageContext}. If {@code null}
    *            then any existing mappings will be removed.
    */
    public static void set(SimpleBag simpleBag) {
        ActiveBaggage.update(BDLContextProvider.set(ActiveBaggage.peek(), Handler.registration(), simpleBag));
    }

    /**
    * <p>
    * Update the {@link SimpleBag} set in {@code baggage}. This method will overwrite any existing
    * SimpleBag set in {@code baggage}.
    * </p>
    *
    * <p>
    * This method does <b>not</b> affect the {@link BaggageContext} being carried by the current thread. To set the
    * {@link SimpleBag} for the current thread, use {@link #set(SimpleBag)}
    * </p>
    *
    * @param baggage A baggage instance to set the {@link SimpleBag} in
    * @param simpleBag the new SimpleBag to set in {@code baggage}. If {@code null}, it will remove any
    *            mapping present.
    * @return a possibly new {@link BaggageContext} instance that contains all previous mappings plus the new mapping.
    */
    public static BaggageContext setIn(BaggageContext baggage, SimpleBag simpleBag) {
        return BDLContextProvider.set(baggage, Handler.registration(), simpleBag);
    }

    @Override
    public BaggageHandler<?> handler() {
        return Handler.instance;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("SimpleBag{\n");
            b.append(this.ids == null ? "" : BDLUtils.indent(String.format("ids = %s\n", BDLUtils.toString(this.ids))));
            b.append("}");
        return b.toString();
    }

    public static class Handler implements BaggageHandler<SimpleBag> {

        public static final Handler instance = new Handler();
        private static BagKey registration = null;

        static synchronized BagKey checkRegistration() {
            registration = BaggageHandlerRegistry.get(instance);
            if (registration == null) {
                _log.error("SimpleBag MUST be registered to a key before it can be propagated.  " +
                "There is currently no registration for SimpleBag and it will not be propagated. " +
                "To register a bag set the bag.{index} property in your application.conf (eg, for " +
                "index 10, bag.10 = \"brown.tracingplane.bdl.examples.SimpleBag\") or with -Dbag.{index} flag " +
                "(eg, for index 10, -Dbag.10=brown.tracingplane.bdl.examples.SimpleBag)");
            }
            return registration;
        }

        static BagKey registration() {
            return registration == null ? checkRegistration() : registration;
        }

        private Handler(){}

        private static final BagKey _idsKey = BagKey.indexed(1);

        private static final Parser<Set<Long>> _idsParser = Parsers.<Long>setParser(Parsers.fixed64Parser());
        private static final Serializer<Set<Long>> _idsSerializer = Serializers.<Long>setSerializer(Serializers.fixed64Serializer());
        private static final Brancher<Set<Long>> _idsBrancher = Branchers.<Long>set();
        private static final Joiner<Set<Long>> _idsJoiner = Joiners.<Long>setUnion();

        @Override
        public boolean isInstance(Bag bag) {
            return bag == null || bag instanceof SimpleBag;
        }

        @Override
        public SimpleBag parse(BaggageReader reader) {
            SimpleBag instance = new SimpleBag();

            if (reader.enter(_idsKey)) {
                instance.ids = _idsParser.parse(reader);
                reader.exit();
            }
            instance._overflow = reader.didOverflow();

            return instance;
        }

        @Override
        public void serialize(BaggageWriter writer, SimpleBag instance) {
            if (instance == null) {
                return;
            }

            writer.didOverflowHere(instance._overflow);

            if (instance.ids != null) {
                writer.enter(_idsKey);
                _idsSerializer.serialize(writer, instance.ids);
                writer.exit();
            }
        }

        @Override
        public SimpleBag branch(SimpleBag instance) {
            if (instance == null) {
                return null;
            }

            SimpleBag newInstance = new SimpleBag();
            newInstance.ids = _idsBrancher.branch(instance.ids);
            return newInstance;
        }

        @Override
        public SimpleBag join(SimpleBag left, SimpleBag right) {
            if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                left.ids = _idsJoiner.join(left.ids, right.ids);
                return left;
            }
        }
    }
}