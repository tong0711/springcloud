package com.landmaster.springboot;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.stream.StreamTransformer;

import javax.cache.processor.MutableEntry;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by tdl on 2017/5/12.
 */
public class IgniteTest {
    public static void main(String[] args) {
        //D:\developer\databases\apache-ignite-fabric-2.0.0-bin\examples\config`
        //try (Ignite ignite = Ignition.start("D://developer/databases/apache-ignite-fabric-2.0.0-bin/examples/config/example-ignite.xml")) {
        //try (Ignite ignite = Ignition.start()) {
        try (Ignite ignite = Ignition.start()) {
            /*Collection<IgniteCallable<Integer>> calls = new ArrayList();
            // Iterate through all the words in the sentence and create Callable jobs.
            for (final String word : "Count characters using callable".split(" ")) {
                calls.add(new IgniteCallable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return word.length();
                    }
                });
            }
            // Execute collection of Callables on the grid.
            Collection<Integer> res = ignite.compute().call(calls);
            int sum = 0;
            // Add up individual word lengths received from remote nodes.
            for (int len : res)
                sum += len;

            System.out.println(">>> Total number of characters in the phrase is '" + sum + "'.");*/
            String[] text = {"1", "1", "1", "1"};
            CacheConfiguration cfg = new CacheConfiguration("wordCountCache");
            IgniteCache<Integer, Long> stmCache = ignite.getOrCreateCache(cfg);
            try (IgniteDataStreamer<String, Long> stmr = ignite.dataStreamer(stmCache.getName())) {
                // Allow data updates.
                stmr.allowOverwrite(true);
                // Configure data transformation to count instances of the same word.
                stmr.receiver(new StreamTransformer<String, Long>() {
                    @Override
                    public Object process(MutableEntry<String, Long> e, Object... args) {
                        // Get current count.
                        Long val = e.getValue();
                        // Increment count by 1.
                        e.setValue(val == null ? 1L : val + 1);
                        System.out.println("xxxx");
                        return null;
                    }
                });
                // Stream words into the streamer cache.
                for (String word : text)
                    stmr.addData(word, 1L);
            }
        }
    }


}
