include "../struct/PPTContentStruct.thrift"
namespace java com.gdut.pptserver.thrift.service

service TOptionService {
    PPTContentStruct.PPTBytes swichPPTPage(1:PPTContentStruct.PPTDetail parm),
}