include "../struct/PPTContentStruct.thrift"
namespace java com.gdut.pptserver.thrift.service

service TParsePPTService {
    PPTContentStruct.PPTBytes parsePPTAndGetFirst(1:PPTContentStruct.PPTDetail parm),
}