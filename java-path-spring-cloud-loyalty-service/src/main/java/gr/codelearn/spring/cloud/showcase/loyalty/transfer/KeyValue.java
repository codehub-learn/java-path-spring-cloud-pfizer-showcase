package gr.codelearn.spring.cloud.showcase.loyalty.transfer;

import lombok.Value;

@Value
public class KeyValue<K, V> {
	K key;
	V value;
}
