meta:
  id: kaitai_insidious_event_parser
seq:
  - id: event
    type: event
types:
  event:
    seq:
      - id: entries
        type: block
        repeat: eos
  block:
    seq:
      - id: magic
        contents: [0x07]
      - id: block
        type: detailed_event_block
    enums:
      identifier:
        7: detailed_event_information
  detailed_event_block:
    seq:
      - id: event_id
        type: u8be
      - id: timestamp
        type: u8be
      - id: probe_id
        type: s4be
      - id: value_id
        type: u8be
      - id: len_serialized_data
        type: u4be
      - id: serialized_data
        size: len_serialized_data
