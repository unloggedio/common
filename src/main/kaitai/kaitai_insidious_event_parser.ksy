meta:
  id: insidious_kaitai_parser
seq:
  - id: event
    type: event
types:
  event:
    seq:
      - id: entries
        type: block
        repeat: eos
        # ^^ repeats until the end of that limited substream
  block:
    seq:
      - id: magic
        type: u1
      - id: block
        type:
          switch-on: magic
          cases:
            1: new_object_block
            2: new_string_block
            3: new_exception_block
            4: data_event_block
            5: type_record_block
            6: weave_information_block
    enums:
      identifier:
        1: new_object
        2: new_string
        3: new_exception
        4: data_event
        5: type_record
        6: weave_information
  new_object_block:
    seq:
      - id: object_id
        type: u8be
      - id: value_id
        type: u8be
  new_string_block:
    seq:
      - id: string_id
        type: u8be
      - id: string_length
        type: u4be
      - id: string
        type: str
        encoding: UTF-8
        size: string_length
  new_exception_block:
    seq:
      - id: exception_length
        type: u4be
      - id: string
        size: exception_length
  data_event_block:
    seq:
      - id: event_id
        type: u8be
      - id: timestamp
        type: u8be
      - id: probe_id
        type: u4be
      - id: value_id
        type: u8be
  type_record_block:
    seq:
      - id: bytes_length
        type: u4be
      - id: type_id
        type: u4be
      - id: type_name_length
        type: u4be
      - id: type_name
        type: str
        encoding: UTF-8
        size: type_name_length
      - id: class_location_length
        type: u4be
      - id: class_location
        type: str
        encoding: UTF-8
        size: class_location_length
      - id: super_class_id
        type: u4be
      - id: component_type_id
        type: u4be
      - id: class_loader_length
        type: u4be
      - id: class_loader
        type: str
        encoding: UTF-8
        size: class_loader_length
  weave_information_block:
    seq:
      - id: string_length
        type: u4be
      - id: weave_bytes
        size: string_length
